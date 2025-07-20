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
        (accessGroup(textInput) ? onJoin() : setError("Passcode not found!"))
    }

    const newGroup = () => {
        if (textInput === ""){
            setError("Please enter a name before submitting!")
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
                        <h1 className="text-4xl font-bold">No Group Found</h1>
                        <p>Please join an existing group or create a new group</p>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-xl mt-2 mr-2"
                        onClick={() => setJoinState(joinState.Passcode)}>Join Existing Group</button>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-xl mt-2"
                        onClick={() => setJoinState(joinState.Create)}>Create New Group</button>
                    </div>
                );
            case joinState.Passcode:
                return(
                    <div>
                        <h1 className="text-4xl font-bold">Join Group</h1>
                        <p>Please enter the group passcode</p>
                        <input 
                        className="bg-white hover:stroke-stone-300 outline-white outline-1 text-xl my-2 mr-1"
                        value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1"
                        onClick={checkPasscode}>Join</button>
                        <br></br>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1"
                        onClick={goBack}>Back</button>
                        <br></br>
                        {error && (
                            <span className="text-red-800 font-bold text-xl">
                            {error}
                            </span>
                        )}
                    </div>
                );
            case joinState.Create:
                return(
                    <div>
                        <h1 className="text-4xl font-bold">Create Group</h1>
                        <p>Please set the group name</p>
                        <input 
                        className="bg-white hover:stroke-stone-300 outline-white outline-1 text-xl my-2 mr-1"
                        value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1"
                        onClick={newGroup}>Create</button>
                        <br></br>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1"
                        onClick={goBack}>Back</button>
                        <br></br>
                        {error && (
                            <span className="text-red-800 font-bold text-xl">
                            {error}
                            </span>
                        )}
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