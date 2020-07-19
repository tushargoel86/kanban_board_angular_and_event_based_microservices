import Task from './Task';

export default interface Kanban {
    id: number,
    tasks: Task[],
    title: string
}