from config import FIREBASE_CONFIG, FIREBASE_CERTIFICATE
from toolkit.database.clients.firebase import Firebase
from toolkit.utils.backup_utils import load_list_from_csv, save_list_to_csv


class ExamsDAO(Firebase):

    def __init__(self) -> None:
        super().__init__(FIREBASE_CONFIG, FIREBASE_CERTIFICATE)

    def restore_db_from_dataset(self, path: str, dataset_path: str):
        _data = load_list_from_csv(dataset_path)
        self.insert_or_update(path=path, data=_data)

    def backup_db_to_dataset(self, path: str, dataset_path: str):
        _data = self.load(path)
        _data = save_list_to_csv(file=dataset_path, data=_data)
