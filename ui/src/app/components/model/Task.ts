import { TaskType } from './TaskType';

export default interface Task {
    id: number,
    description: string,
    status: TaskType
}