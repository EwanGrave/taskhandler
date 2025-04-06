import { TaskDTO, UserDTO } from '../../../api';

export interface TaskDataType {
  task: TaskDTO;
  users: UserDTO[];
  deleteTaskCallback: () => void;
}
