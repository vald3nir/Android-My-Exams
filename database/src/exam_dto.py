from dataclasses import dataclass
from typing import Any


@dataclass
class ExamDTO:
    date: str
    total_cholesterol: int
    HDL_D: int
    NOT_HDL: int
    LDL: int
    triglycerides: int
    uric_acid: float

    @staticmethod
    def from_dict(obj: Any) -> 'ExamDTO':
        return ExamDTO(
            date=obj.get("date"),
            total_cholesterol=obj.get("total_cholesterol"),
            HDL_D=obj.get("HDL_D"),
            NOT_HDL=obj.get("NOT_HDL"),
            LDL=obj.get("LDL"),
            triglycerides=obj.get("triglycerides"),
            uric_acid=obj.get("uric_acid")
        )

    def to_json(self):
        return {
            "date": self.date,
            "total_cholesterol": self.total_cholesterol,
            "HDL_D": self.HDL_D,
            "NOT_HDL": self.NOT_HDL,
            "LDL": self.LDL,
            "triglycerides": self.triglycerides,
            "uric_acid": self.uric_acid,
        }

    @staticmethod
    def schema_json(obj: dict) -> dict:
        return ExamDTO.from_dict(obj).to_json()
