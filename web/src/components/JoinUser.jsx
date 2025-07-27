import { useState } from "react"
import { joinState } from "../constraints/joinState"
import { accessUser } from "../api/auth"
import { createUser } from "../api/users"
import Modal from "./Modal"

function JoinUser({onJoin}) {

    const [currentJoinState, setJoinState] = useState(joinState.None)
    const [textInput, setTextInput] = useState("")
    const [error, setError] = useState("")
    const [newPasscode, setNewPasscode] = useState("")
    const [open, setOpen] = useState(false)

    const handleClose = () => {
        setOpen(false);
        onJoin()
    };

    const handleOpen = () => {
        setOpen(true);
    };

    const checkPasscode = () => {
        if (textInput === ""){
            return
        }
        (accessUser(textInput) ? onJoin() : setError("Passcode not found!"))
    }

    const newUser = () => {
        if (textInput === ""){
            setError("Please enter a name before submitting!")
            return
        }
        const passcode = createUser(textInput)
        setNewPasscode(passcode)
        handleOpen()
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
                        <h1 className="text-4xl font-bold">No User Found</h1>
                        <p>Please join as an existing user or create a new user</p>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-xl mt-2 mr-2"
                        onClick={() => setJoinState(joinState.Passcode)}>Join as User</button>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-xl mt-2"
                        onClick={() => setJoinState(joinState.Create)}>Create New User</button>
                    </div>
                );
                        case joinState.Passcode:
                return(
                    <div>
                        <h1 className="text-4xl font-bold">Join as User</h1>
                        <p>Please enter your passcode</p>
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
                        <h1 className="text-4xl font-bold">Create User</h1>
                        <p>Please enter your name</p>
                        <input 
                        className="bg-white hover:stroke-stone-300 outline-white outline-1 text-xl my-2 mr-1"
                        value={textInput} onChange={e => setTextInput(e.target.value)}></input>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1"
                        onClick={newUser}>Create</button>
                        <Modal isOpen={open} onClose={handleClose}>
                            <>
                                <h1 className="text-4xl font-bold">User Created</h1>
                                <p className="text-2xl my-2">Your passcode is: {newPasscode}</p>
                                <p>Please save this if you wish to access your user session from another device, 
                                    or if your browser doesn't save cookies (this is standard when in "private" browsing modes)
                                </p>
                                <button
                                className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid text-white text-xl px-1 mt-2" 
                                onClick={handleClose}>Continue</button>
                            </>
                        </Modal>
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
            default:
                setJoinState(joinState.None)
        }
    }

    return(
        <>
        <div className="flex justify-center">
            {renderComponent()}
        </div>
        </>
    )
}

export default JoinUser