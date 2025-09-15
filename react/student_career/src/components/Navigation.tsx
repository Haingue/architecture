import { Link } from "react-router"

const Navigation = () => {
  return (
    <div className="text-3xl font-bold text-center">
      <Link className="hover:text-purple-700" to={"/"}>Home</Link>
      <br />
      <Link className="hover:text-purple-700" to={"/job-offer"}>Offer</Link>
      <span className="mx-10">|</span>
      <Link className="hover:text-purple-700" to={"/job-demand"}>Demand</Link> 
    </div>
  )
}

export default Navigation