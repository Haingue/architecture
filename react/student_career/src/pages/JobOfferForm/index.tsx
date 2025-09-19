import { useLocation, useNavigate } from "react-router"
import Button from "../../components/Button/Button"
import { useState, type BaseSyntheticEvent } from "react"

const HOST = 'http://localhost:8090'

const JobOfferForm = () => {
  const location = useLocation()
  const navigate = useNavigate()
  const [jobOffer, setJobOffer] = useState(location.state?.jobOffer || {})
  
  const isNew:boolean = () => location.state.isNew

  const saveJobOffer = async () => {
    const response = await fetch(`${HOST}/service/job-offer`,
      {
        method: isNew() ? "Post" : "Put",
        headers: {
          'Authorization': `Basic ${btoa("user" + ':' + "strongpassword")}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(jobOffer)
      })
    if ((isNew() && response.status === 201) || response.status === 200) {
      navigate('/job-offer')
    } else {
      alert(await response.text())
    }
  }

  const updateField = (event: BaseSyntheticEvent) => {
    setJobOffer({ ...jobOffer, [event.target.id]: event.target.value})
  }

  return (
    <>
      <h1>{jobOffer.title}</h1>
      <div className="grid gap-6 mb-6 md:grid-cols-2">
        <div className="w-full px-2">
          <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">title</label>
          <input id="title" onChange={updateField} className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" value={jobOffer.title} />
        </div>
        <div className="w-full px-2">
          <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">description</label>
          <textarea id="description" onChange={updateField} className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" value={jobOffer.description} />
        </div>
        <div className="w-full px-2">
          <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">company</label>
          <input id="owner" onChange={(event: BaseSyntheticEvent) => updateField({...event, target: {...event.target, id: 'owner', value: {name: event.target.value}}})} className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" value={jobOffer.owner?.name} />
        </div>
      </div>
      <div>
        <Button onClick={saveJobOffer}>Save</Button>
      </div>
    </>
  )
}

export default JobOfferForm