import { EventModel } from '@/models/EventService.model'
import { useState } from 'react'
import { useLocation } from 'react-router-dom'
import { v4 as uuidv4} from 'uuid'

export type EventOrganizationProps = {
    event?: EventModel
    isNewEvent?: boolean
}
const EventOrganizationPage = (props: EventOrganizationProps) => {
    const location = useLocation();
    const [id, setId] = useState(props.event?.id)
    const [title, setTitle] = useState(props.event?.title)
    const [description, setDescription] = useState(props.event?.description)
    const [ticketPrice, setTicketPrice] = useState(props.event?.ticketPrice)
    const [datetime, setDatetime] = useState(props.event?.datetime)
    const [isLoaded, setIsLoaded] = useState(false)
    if (!isLoaded) {
        console.debug("Load event detail from state: ", location.state == null || location.state.isNewEvent, location.state)
        if (location.state == null || location.state.isNewEvent) {
            // load event data from event service
            setId(uuidv4())
            setTitle("")
            setDescription("")
            setTicketPrice(0.0)
            /*setOrganizer(uuidv4())
            setLocation({
                "address": "Rue Guglielmo Marconi, 59650 Villeneuve-d'Ascq",
                "capacity": 1500
            })*/
            setDatetime(new Date())
        } else {
            // load event data from state
            setId(location.state.event.id)
            setTitle(location.state.event.title)
            setDescription(location.state.event.description)
            setTicketPrice(location.state.event.ticketPrice)
            /*setOrganizer(location.state.event.organizer)
            setLocation(location.state.event.location)*/
            setDatetime(location.state.event.datetime)
        }
        setIsLoaded(true)
    }
    return (
    <>
        { props.isNewEvent ?
            <h1>Organize new event</h1>
            :
            <h1>Update event</h1>
        }
        <div>
            <div className="mb-6">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Id</label>
                <input className="mb-6 bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500" value={id} disabled />
            </div>
            <div className="mb-6">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Title</label>
                <input className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 text-base focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" type="text" value={title} onChange={e => setTitle(e.target.value)} />
            </div>
            <div className="mb-6">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description</label>
                <input className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 text-base focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" type="textarea" value={description} onChange={e => setDescription(e.target.value)} />
            </div>
            <div className="mb-6">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">TicketPice</label>
                <input className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 text-base focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" type="number" value={ticketPrice} step={0.1} onChange={e => setTicketPrice(parseFloat(e.target.value))} />
            </div>
            <div className="mb-6">
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Datetime</label>
                <input className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 text-base focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" type="datetime" value={datetime?.toISOString()} onChange={e => setDatetime(new Date(e.target.value))} />
            </div>
        </div>
        <div className="fixed bottom-0 w-full text-center">
            <button type="button" className="my-2 text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:outline-none focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center me-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">
                <svg className="w-3.5 h-3.5 me-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 21">
                    <path fill="currentColor" d="M4 9.05H3v2h1v-2Zm16 2h1v-2h-1v2ZM10 14a1 1 0 1 0 0 2v-2Zm4 2a1 1 0 1 0 0-2v2Zm-3 1a1 1 0 1 0 2 0h-2Zm2-4a1 1 0 1 0-2 0h2Zm-2-5.95a1 1 0 1 0 2 0h-2Zm2-3a1 1 0 1 0-2 0h2Zm-7 3a1 1 0 0 0 2 0H6Zm2-3a1 1 0 1 0-2 0h2Zm8 3a1 1 0 1 0 2 0h-2Zm2-3a1 1 0 1 0-2 0h2Zm-13 3h14v-2H5v2Zm14 0v12h2v-12h-2Zm0 12H5v2h14v-2Zm-14 0v-12H3v12h2Zm0 0H3a2 2 0 0 0 2 2v-2Zm14 0v2a2 2 0 0 0 2-2h-2Zm0-12h2a2 2 0 0 0-2-2v2Zm-14-2a2 2 0 0 0-2 2h2v-2Zm-1 6h16v-2H4v2ZM10 16h4v-2h-4v2Zm3 1v-4h-2v4h2Zm0-9.95v-3h-2v3h2Zm-5 0v-3H6v3h2Zm10 0v-3h-2v3h2Z"/>
                </svg>
                <span className="hidden md:contents">Valid</span>
            </button>
        </div>
    </>
    )
}
export default EventOrganizationPage