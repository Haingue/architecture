import { useState } from "react"
import Button from "../../components/Button/Button"
import { useLocation, useNavigate } from "react-router"

const HOST = 'http://localhost:8080'

const JobOfferSummary = ({dto={}, onUpdate=()=>{}, onCancel=()=>{}}) => {
  const cancelJobOffer = async (jobOfferId: string) => {
    await fetch(`${HOST}/service/job-offer/${jobOfferId}`,
      {
        method: "Delete",
        headers: {
          'Authorization': `Basic ${btoa("user" + ':' + "strongpassword")}`
        }
      }
    )
    onCancel()
  }

  return (
    <tr>
      <td>{dto.id}</td>
      <td>{dto.title}</td>
      <td>{dto.description}</td>
      <td>
        <Button onClick={onUpdate}>update</Button>
        <Button onClick={() => cancelJobOffer(dto.id)}>cancel</Button>
      </td>
    </tr>
  )
}

const JobOfferHome = () => {
  const navigate = useNavigate();
  const [offers, setOffers] = useState<[]>([])
  const [titleFilter, setTitleFilter] = useState<string>('*')
  const [filterTimeoutId, setFilterTimeoutId] = useState<ReturnType<typeof setTimeout>>()

  const searchJobOffer = async () => {
    setOffers([])
    const formData: FormData = new FormData()
    formData.append('page', '0')
    formData.append('size', '10')
    if (titleFilter) {
      formData.append('title', titleFilter)
    } else {
      formData.append('title', '%')
    }
    const response = await fetch(`${HOST}/service/job-offer?title=${titleFilter}&page=0&size=10`)
    if (response.status === 200) {
      const pageRespone = await response.json()
      console.log('Response:', pageRespone)
      const results = pageRespone.content.map((element, key) =>
        <JobOfferSummary key={key} dto={element} onUpdate={() => updateJobOffer(element)} onCancel={searchJobOffer}/>
      );
      setOffers(results)
    }
  }

  const updateTitleFilter = () => {
    clearTimeout(filterTimeoutId)
    const newFilterTimeoutId: ReturnType<typeof setTimeout> = setTimeout(searchJobOffer, 1000)
    setFilterTimeoutId(newFilterTimeoutId)
  }

  const updateJobOffer = (jobOffer) => {
    navigate('/job-offer/form', { state: {jobOffer}})
  }

  return (
    <>
      <h1>JobOfferHome</h1>
      <div>
        <section className="flex">
          <div className="flex-5">
            <input className="border-2 border-gray-400 rounded-sm mr-4" placeholder="Title" onChange={updateTitleFilter} />
            <Button onClick={searchJobOffer}>refresh</Button>
          </div>
          <div>
            <Button onClick={() => navigate('/job-offer/form', {state: {isNew:true}})}>Publish new JobOffer</Button>
          </div>
        </section>
        <div>
          <table className="w-full text-center">
            <thead>
              <tr>
                <th>id</th>
                <th>title</th>
                <th>description</th>
                <th>action</th>
              </tr>
            </thead>
            <tbody>
              {offers}
            </tbody>
          </table>
        </div>
      </div>
    </>
  )
}

export default JobOfferHome