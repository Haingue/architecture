import { Route, Routes } from 'react-router'
import Home from './pages/Home'
import NotFound from './pages/NotFound'
import Navigation from './components/Navigation'
import JobOfferHome from './pages/JobOfferHome'
import JobDemandHome from './pages/JobDemandHome'
import JobOfferForm from './pages/JobOfferForm'

const Root = () => {
  return (
    <>
      <div className="flex flex-col min-h-screen">
          <header className="w-full text-center h-1/12">
              <Navigation />
          </header>
          <main className='w-full flex-grow p-4'>
              <Routes>
                  <Route path='/' element={<Home/>} />
                  <Route path='/job-offer' element={<JobOfferHome/>} />
                  <Route path='/job-offer/form' element={<JobOfferForm />} />
                  <Route path='/job-demand' element={<JobDemandHome/>} />
                  <Route path='*' element={<NotFound/>} />
              </Routes>
          </main>
          <footer className="w-full h-5 text-gray-400 flex items-center justify-center">
              MSI 2025-2026
          </footer>
        </div>
    </>
  )
}

export default Root