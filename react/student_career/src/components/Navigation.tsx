import { useEffect, useRef, useState } from "react"
import { Link } from "react-router"

const HOST = 'http://localhost:8080'

const Navigation = () => {
  const [jobOfferNotification, setJobOfferNotification] = useState<number[]>([])
  const notificationNumber = useRef(jobOfferNotification)

  useEffect(() => {
    notificationNumber.current = [...jobOfferNotification]
  }, [jobOfferNotification])

  useEffect(() => {
      const evtSource = new EventSource(`${HOST}/service/job-offer/subscribe`,
      {
        withCredentials: true
      })
      evtSource.onmessage = (event) => {
        console.log('Event: ', event)
        setJobOfferNotification([...notificationNumber.current, JSON.parse(event.data).id])
      }
      evtSource.onopen = () => console.debug(">>> Connection opened!");
      evtSource.onerror = (e) => console.error("ERROR!", e);

      return () => evtSource.close()
  }, [])

  return (
    <div className="text-3xl font-bold text-center">
      <Link className="hover:text-purple-700" to={"/"}>Home</Link>
      <br />
      <Link className="hover:text-purple-700" to={"/job-offer"}>
        <span>Offer</span>
        <span className="bg-red-100 text-red-800 text-xs font-medium me-2 px-2.5 py-0.5 rounded-sm dark:bg-red-900 dark:text-red-300">{jobOfferNotification.length}</span>
      </Link>
      <span className="mx-10">|</span>
      <Link className="hover:text-purple-700" to={"/job-demand"}>Demand</Link> 
    </div>
  )
}

export default Navigation