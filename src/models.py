from dataclasses import asdict, dataclass
from datetime import datetime, timezone


@dataclass(slots=True)
class Event:
    id: int
    message: str
    timestamp: str

    @staticmethod
    def now_iso() -> str:
        return datetime.now(timezone.utc).isoformat()

    def to_dict(self) -> dict:
        return asdict(self)
