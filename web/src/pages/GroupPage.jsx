import { useEffect, useState } from "react";
import { checkSession } from "../api/auth";
import { MoonLoader } from 'react-spinners';
import JoinGroup from "../components/JoinGroup";
import GroupPanel from "../components/GroupPanel";

function Groups() {

  const [loading, setLoading] = useState(true);
  const [status, setStatus] = useState(null);

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
    <div className="flex justify-center pt-4">
      {status.hasGroup ? <GroupPanel user={status.hasUser} /> : <JoinGroup onJoin={() => setStatus({ hasGroup: true, hasUser: false })}/>}
    </div>
    </>
  )
};

export default Groups;
