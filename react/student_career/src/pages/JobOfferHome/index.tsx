import { useState } from "react"
import Button from "../../components/Button/Button"

const JobOfferHome = () => {
  const [offers, setOffers] = useState<[]>([])
  const [titleFilter, setTitleFilter] = useState<string>('*')
  const [filterTimeoutId, setFilterTimeoutId] = useState<ReturnType<typeof setTimeout>>()

  const searchJobOffer = async (title: string) => {
    const formData: FormData = new FormData()
    formData.append('page', '0')
    formData.append('size', '10')
    if (title) {
      formData.append('title', title)
    } else {
      formData.append('title', '%')
    }
    const response = await fetch(`http://localhost:8080/service/job-offer?title=${title}&page=0&size=10`)
    if (response.status === 200) {
      const pageRespone = await response.json()
      console.log('Response:', pageRespone)
      const results = pageRespone.content.map(element =>
        <tr>
          <td>{element.id}</td>
          <td>{element.title}</td>
          <td>{element.description}</td>
          <td>
            <Button>update</Button>
            <Button>cancel</Button>
          </td>
        </tr>
      );
      setOffers(results)
    }
  }

  const updateTitleFilter = () => {
    clearTimeout(filterTimeoutId)
    const newFilterTimeoutId: ReturnType<typeof setTimeout> = setTimeout(() => searchJobOffer(titleFilter), 1000)
    setFilterTimeoutId(newFilterTimeoutId)
  }

  return (
    <>
      <h1>JobOfferHome</h1>
      <div>
        <div>
          <input placeholder="Title" onChange={updateTitleFilter} />
          <Button onClick={() => searchJobOffer(titleFilter)}>refresh</Button>
        </div>
        <div>
          <table>
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