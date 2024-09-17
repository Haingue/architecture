export type EventModel = {
    id: string,
    title?: string,
    description?: string,
    ticketPrice?: number,
    organizer: string,
    location?: Location,
    datetime?: Date,
}

export type Location = {
    address: string,
    capacity?: number,
}