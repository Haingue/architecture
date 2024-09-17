import EventDetails from '@/components/card_event/EventDetails'
import PaymentModal from '@/components/payment/modal_payment'
import { EventModel } from '@/models/EventService.model'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { v4 as uuidv4 } from 'uuid'

const eventList: EventModel[] = []
for (let i = 0; i < 10; i++) {
    eventList.push(
        {
            "id": uuidv4(),
            "title": `IMT - Cours architectur ${i}`,
            "description": "Cours d'architecture logiciel",
            "ticketPrice": 5.5,
            "organizer": uuidv4(),
            "location": {
                "address": "Rue Guglielmo Marconi, 59650 Villeneuve-d'Ascq",
                "capacity": 1500
            },
            "datetime": new Date("2024-09-13T13:10:38.020Z")
        }
    )
}

const EventPage = () => {
    const navigate = useNavigate();
    const [buyEventId, setBuyEventId] = useState<string | undefined>(undefined)

    function goToOrganizationVeiw (event?: EventModel) {
        navigate("/event/organization", { state: {
            isNewEvent: event === undefined,
            event,
        }})
    }

    return (
    <>
        { buyEventId ? 
            <PaymentModal eventId={buyEventId} onPaymentDone={() => setBuyEventId(undefined)} onCancel={() => setBuyEventId(undefined)}/>
            :
            <></>
        }

        <h1>Event list</h1>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            {eventList.map((e) => {
                return <EventDetails event={e} handleClick={() => goToOrganizationVeiw(e)} handleBuy={(e: EventModel) => setBuyEventId(e.id)} />
            })}
        </div>
        <div className="fixed bottom-0 w-full text-center">
            <button type="button" onClick={() => goToOrganizationVeiw()} className="my-2 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                <svg className="w-3.5 h-3.5 me-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 21">
                    <path fill="currentColor" d="M4 9.05H3v2h1v-2Zm16 2h1v-2h-1v2ZM10 14a1 1 0 1 0 0 2v-2Zm4 2a1 1 0 1 0 0-2v2Zm-3 1a1 1 0 1 0 2 0h-2Zm2-4a1 1 0 1 0-2 0h2Zm-2-5.95a1 1 0 1 0 2 0h-2Zm2-3a1 1 0 1 0-2 0h2Zm-7 3a1 1 0 0 0 2 0H6Zm2-3a1 1 0 1 0-2 0h2Zm8 3a1 1 0 1 0 2 0h-2Zm2-3a1 1 0 1 0-2 0h2Zm-13 3h14v-2H5v2Zm14 0v12h2v-12h-2Zm0 12H5v2h14v-2Zm-14 0v-12H3v12h2Zm0 0H3a2 2 0 0 0 2 2v-2Zm14 0v2a2 2 0 0 0 2-2h-2Zm0-12h2a2 2 0 0 0-2-2v2Zm-14-2a2 2 0 0 0-2 2h2v-2Zm-1 6h16v-2H4v2ZM10 16h4v-2h-4v2Zm3 1v-4h-2v4h2Zm0-9.95v-3h-2v3h2Zm-5 0v-3H6v3h2Zm10 0v-3h-2v3h2Z"/>
                </svg>
                <span className="hidden md:contents">Organize your event</span>
            </button>
        </div>
    </>)
}
export default EventPage