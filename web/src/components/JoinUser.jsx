import { useState } from "react"
import { joinState } from "../constraints/joinState"
import { accessUser } from "../api/auth"
import { createUser } from "../api/users"

function JoinUser({onJoin}) {

    const [currentJoinState, setJoinState] = useState(joinState.None)

    const [textInput, setTextInput] = useState("")

    const [error, setError] = useState("")

    const checkPasscode = () => {
        if (textInput === ""){
            return
        }
        (accessUser(textInput) ? onJoin() : setError("Passcode not found"))
    }

    const newUser = () => {
        if (textInput === ""){
            setError("Please enter a name before submitting")
            return
        }
        createUser(textInput)
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
                        <h1>No User Found</h1>
                        <p>Please join as an existing user or create a new user</p>
                        <button onClick={() => setJoinState(joinState.Passcode)}>Join as User</button>
                        <button onClick={() => setJoinState(joinState.Create)}>Create New User</button>
                    </div>
                );
                        case joinState.Passcode:
                return(
                    <div>
                        <h1>Join as User</h1>
                        <p>Please enter your passcode</p>
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
                        <h1>Create User</h1>
                        <p>Please enter your name</p>
                        <input value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button onClick={newUser}>Create</button>
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

export default JoinUser