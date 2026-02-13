from http import HTTPStatus

from flask import Blueprint, jsonify, request

from src.store import InMemoryEventStore

logs_bp = Blueprint("logs", __name__)
store = InMemoryEventStore()


@logs_bp.post("/log")
def create_log():
    payload = request.get_json(silent=True) or {}
    message = payload.get("message")

    if not isinstance(message, str) or not message.strip():
        return (
            jsonify(
                {
                    "status": "error",
                    "error": {
                        "code": "INVALID_PAYLOAD",
                        "message": "Field 'message' is required and must be a non-empty string.",
                    },
                }
            ),
            HTTPStatus.BAD_REQUEST,
        )

    event = store.add_event(message=message.strip())
    return (
        jsonify(
            {
                "status": "success",
                "data": event.to_dict(),
            }
        ),
        HTTPStatus.CREATED,
    )


@logs_bp.get("/logs")
def get_logs():
    events = [event.to_dict() for event in store.list_events()]
    return (
        jsonify(
            {
                "status": "success",
                "data": events,
                "meta": {"count": len(events)},
            }
        ),
        HTTPStatus.OK,
    )
