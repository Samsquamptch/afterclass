import { useEffect, useState } from "react";
import { MoonLoader } from 'react-spinners';
import { getGroup } from "../api/groups.js"
import JoinUser from "./JoinUser.jsx";

function GroupPanel(props) {

    const [loading, setLoading] = useState(true);
    const [status, setStatus] = useState(null);
    const [groupData, setGroupData] = useState(null)

    useEffect(() => {
      async function loadGroups() {
        const loadedData = await getGroup()
        setGroupData(loadedData)
        setStatus(props.user)
        setLoading(false)
      }
      loadGroups()
    },[]);

    if (loading) return <MoonLoader color="#4C0B16" speedMultiplier={0.75}/>;

    return(
        <>
          <div>
            <div>
              <p>{JSON.stringify(groupData)}</p>
            </div>
            <div>
              {status ? <h1>User</h1> : <JoinUser onJoin={() => setStatus(true)}/>}
            </div>
          </div>
        </>
    )
}

export default GroupPanel