import { TaskType } from './TaskType';

export default interface Task {
    taskId: string,
    boardTitle: string, 
    description: string,
    taskType: TaskType
}