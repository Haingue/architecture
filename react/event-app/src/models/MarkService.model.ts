import { EventModel } from './EventService.model';

export type Mark = {
    id: string,
    event: EventModel,
    participant: string,
    value: number,
    creationDatetime: Date,
}