import { useState, useEffect } from "react"
import { getUser } from "../api/users"
import { MoonLoader } from "react-spinners";
import { addLesson } from "../api/lessons";
import LessonCard from "./LessonCard";
import Modal from "./Modal";

function UserPanel({onUpdate}) {

    const [loading, setLoading] = useState(true);
    const [userData, setUserData] = useState(null)
    const [lessons, setLessons] = useState([])
    const [error, setError] = useState("")
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    const handleOpen = () => {
        setError("")
        setOpen(true);
    };

    const newLesson = async (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);
        const name = formData.get("LessonName");
        const day = formData.get("lessonDay");
        const start = formData.get("startTime");
        const end = formData.get("endTime");
        if (name === "" || start === "" || end === "") {
            setError("Please ensure no fields are empty")
        } else {
            let newLesson = {
                name: name,
                weekday: day,
                startTime: start,
                endTime: end};
            newLesson = await addLesson(newLesson)
            setLessons(lessons => [...lessons, newLesson]);
            handleClose()
            onUpdate()
        }
    }

    useEffect(() => {
        async function loadUser() {
            const loadedData = await getUser()
            setUserData(loadedData)
            const userLessons = loadedData.lessons.map(l => l)
            setLessons(userLessons)
            setLoading(false)
        }
        loadUser()
    }, [])

    const  deleteLesson = async (index) => {
        const updatedLessons = lessons.filter((_, i) => i !==index)
        setLessons(updatedLessons);
        onUpdate()
    }

    const editLesson = () => {
        onUpdate()
    }

    if (loading) return(
        <>
        <div className="flex justify-center">
            <MoonLoader color="#4C0B16" speedMultiplier={0.75}/>
        </div>
    </>
    )

    return(
        <>
        <div className="relative">
            <h2 className="text-4xl font-bold pb-1">{userData.name}</h2>
            <button 
            className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid px-1 text-white text-lg my-2 absolute top-0 right-16"
            onClick={handleOpen}>Add Lesson</button>
            <Modal isOpen={open} onClose={handleClose}>
                <>
                    <h1 className="text-2xl font-bold">Add New Lesson</h1>
                    <form onSubmit={newLesson} className="text-xl relative">
                        <label className="font-archivo">
                            Name: <input name="LessonName" size="16" className="bg-white my-1 py-1"/>
                        </label>
                        <br/>
                        <label>
                            Day: <select name="lessonDay" className="bg-white my-1 p-1">
                                    <option value="Monday">Monday</option>
                                    <option value="Tuesday">Tuesday</option>
                                    <option value="Wednesday">Wednesday</option>
                                    <option value="Thursday">Thursday</option>
                                    <option value="Friday">Friday</option>
                                    <option value="Saturday">Saturday</option>
                                    <option value="Sunday">Sunday</option>
                                </select>
                        </label>
                        <br/>
                        <label>
                            Start Time: <input type="time" name="startTime" className="bg-white my-1 p-1"/>
                        </label>
                        <br/>
                        <label>
                            End Time: <input type="time" name="endTime" className="bg-white my-1 p-1"/>
                        </label>
                        <br/>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white mt-5 mr-4"
                        type="submit">Add</button>
                        <button 
                        className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white"
                        type="button" onClick={handleClose}>Cancel</button>
                        <br></br>
                        {error && (
                            <span className="text-red-800 text-base">
                                {error}
                            </span>
                        )}
                    </form>
                </>
            </Modal>
        </div>
        <div className="grid grid-cols-3 grid-rows-2">
            {lessons.map((lesson, index) => (
                <LessonCard
                    key={lesson.id}
                    lesson={lesson}
                    index={index}
                    onDelete={deleteLesson}
                    onEdit={editLesson}
                />
            ))}
        </div>
        </>
    )
}

export default UserPanel