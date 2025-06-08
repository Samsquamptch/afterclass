import { useEffect, useState } from "react";
import { checkSession } from "../api/auth";
import { MoonLoader } from 'react-spinners';
import JoinGroup from "../components/JoinGroup";

function Groups() {

  const [loading, setLoading] = useState(true);
  const [status, setStatus] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleClose = () => {
    setIsModalOpen(false);
  };

    useEffect(() => {
      async function checkAuth() {
        const result = await checkSession();
        setStatus(result)
        setLoading(false)
      }
      checkAuth()
    },[]);

  if (loading) return <MoonLoader color="#4C0B16" speedMultiplier={0.75}/>;
  
  return(
    <>
    <div className="group-panel">
      {status.hasGroup ? <h1>Groups</h1> : <JoinGroup/>}
    </div>
    </>
  )
};

export default Groups;
