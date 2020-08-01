import Task from './Task';

export default interface Kanban {
    tasks ?: Task[],
    title: string
}