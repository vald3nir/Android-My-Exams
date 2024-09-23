from src.exam_dto import ExamDTO
from src.exams_dao import ExamsDAO
from toolkit.utils.backup_utils import save_list_to_csv
from toolkit.utils.io_utils import list_all_files, delete_folder, create_folder

dao = ExamsDAO()

_ROOT_PATH = "/"
_DATASETS_PATH = "datasets/"


def backup():
    clear_dataset_folder()
    for client in dao.load(_ROOT_PATH):
        _data = dao.load(_ROOT_PATH + client)
        _data = [ExamDTO.schema_json(obj) for obj in _data]
        save_list_to_csv(file=_DATASETS_PATH + client + ".csv", data=_data)


def clear_dataset_folder():
    delete_folder(_DATASETS_PATH)
    create_folder(_DATASETS_PATH)


def restore():
    for dataset in list_all_files(_DATASETS_PATH):
        user_name = user_name_from_dataset(dataset)
        dao.restore_db_from_dataset(path=user_name, dataset_path=dataset)


def user_name_from_dataset(file_path: str) -> str:
    return file_path.replace(f"{_DATASETS_PATH}", "").replace(".csv", "")
