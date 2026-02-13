from threading import Lock

from src.models import Event


class InMemoryEventStore:
    """Thread-safe in-memory storage for events."""

    def __init__(self) -> None:
        self._events: list[Event] = []
        self._next_id = 1
        self._lock = Lock()

    def add_event(self, message: str) -> Event:
        with self._lock:
            event = Event(id=self._next_id, message=message, timestamp=Event.now_iso())
            self._events.append(event)
            self._next_id += 1
            return event

    def list_events(self) -> list[Event]:
        with self._lock:
            return list(self._events)
