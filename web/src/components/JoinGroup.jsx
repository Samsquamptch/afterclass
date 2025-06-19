import { useState } from "react"
import { accessGroup } from "../api/auth"
import { createGroup } from "../api/groups.js"
import { joinState } from "../constraints/joinState.js"

function JoinGroup({onJoin}) {

    const [currentJoinState, setJoinState] = useState(joinState.None)

    const [textInput, setTextInput] = useState("")

    const [error, setError] = useState("")

    const checkPasscode = () => {
        if (textInput === ""){
            return
        }
        (accessGroup(textInput) ? onJoin() : setError("Passcode not found"))
    }

    const newGroup = () => {
        if (textInput === ""){
            setError("Please enter a name before submitting")
            return
        }
        createGroup(textInput)
        onJoin()
    }

    const goBack = () => {
        setError("")
        setTextInput("")
        setJoinState(joinState.None)
    }

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
                        <input value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button onClick={checkPasscode}>Join</button>
                        {error && (
                            <span style={{ marginLeft: '10px', color: 'red' }}>
                            {error}
                            </span>
                        )}
                        <br></br>
                        <button onClick={goBack}>Back</button>
                    </div>
                );
            case joinState.Create:
                return(
                    <div>
                        <h1>Create Group</h1>
                        <p>Please set the group name</p>
                        <input value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button onClick={newGroup}>Create</button>
                        {error && (
                            <span style={{ marginLeft: '10px', color: 'red' }}>
                            {error}
                            </span>
                        )}
                        <br></br>
                        <button onClick={goBack}>Back</button>
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