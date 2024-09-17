import { EventModel } from "@/models/EventService.model"
import { Mark } from "@/models/MarkService.model"
import { v4 } from "uuid"

const StarsEvaluation = (event: EventModel) => {

    function sendEvaluation (starNumber: number) {
        const mark: Mark = {
            id: v4(),
            event: event,
            value: starNumber,
            participant: v4(),
            creationDatetime: new Date()
        }
        console.debug('Send evaluation', mark)
    }

    function generateStars (event: EventModel, mark: number) {
        const stars = []
        for(let star=0; star < 5; star++) {
            let textColor = "text-yellow-300"
            if (mark >= star) {
                textColor = "text-gray-300 dark:text-gray-500"
            }
            stars.push(
                <svg onClick={() => sendEvaluation(star)} className={textColor+" w-4 h-4 ms-1"} aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 22 20">
                    <path d="M20.924 7.625a1.523 1.523 0 0 0-1.238-1.044l-5.051-.734-2.259-4.577a1.534 1.534 0 0 0-2.752 0L7.365 5.847l-5.051.734A1.535 1.535 0 0 0 1.463 9.2l3.656 3.563-.863 5.031a1.532 1.532 0 0 0 2.226 1.616L11 17.033l4.518 2.375a1.534 1.534 0 0 0 2.226-1.617l-.863-5.03L20.537 9.2a1.523 1.523 0 0 0 .387-1.575Z"/>
                </svg>
            )
        }
        return stars
    }

    return (
        <>
        <div className="flex items-center">
            {generateStars(event, 0)}
        </div>
        </>
    )
}
export default StarsEvaluation