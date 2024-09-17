import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Sidebar from '@/components/navigation/Sidebar';
import Home from '@/pages/Home';
import EventPage from '@/pages/event/event';
import EventOrganizationPage from './pages/event/event_organization';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>,
  },
  {
    path: "/event",
    element: <EventPage/>,
  },
  {
    path: "/event/organization",
    element: <EventOrganizationPage/>,
  },
]);

function App() {
  return (
    <>
    <Sidebar/>
    <main className="p-4 sm:ml-64">
      <RouterProvider router={router} />
    </main>
    </>
  )
}

export default App
