import { useState } from "react"

const joinState = {
    None: 'NONE',
    Create: 'CREATE',
    Passcode: 'PASSCODE'
}

function JoinGroup() {

    const [currentJoinState, setJoinState] = useState(joinState.None)

    const renderComponent = () => {
        switch (currentJoinState) {
            case joinState.None:
                return(
                    <div>
                        <h1>No Group Found</h1>
                        <p>Please join an existing group or create a new group</p>
                        <button onClick={() => setJoinState(joinState.Passcode)}>Join Existing Group</button>
                        <button onClick={() => setJoinState(joinState.Create)}>Create New Group</button>
                    </div>
                );
            case joinState.Passcode:
                return(
                    <div>
                        <h1>Join Group</h1>
                        <p>Please enter the group passcode</p>
                        <input name="passcodeInput"></input>
                        <button>Join</button>
                        <br></br>
                        <button onClick={() => setJoinState(joinState.None)}>Back</button>
                    </div>
                );
            case joinState.Create:
                return(
                    <div>
                        <h1>Create Group</h1>
                        <p>Please set the group name</p>
                        <input name="nameInput"></input>
                        <button>Create</button>
                        <br></br>
                        <button onClick={() => setJoinState(joinState.None)}>Back</button>
                    </div>
                );
        }
    }

    return(
        <>
           {renderComponent()} 
        </>
    )
}

export default JoinGroup