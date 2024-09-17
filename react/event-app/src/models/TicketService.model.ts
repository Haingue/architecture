import { EventModel } from './EventService.model';

export type Ticket = {
    id: string,
    event: EventModel,
    participant: string,
    paymentDatetime: Date,
    creationDatetime: Date,
}