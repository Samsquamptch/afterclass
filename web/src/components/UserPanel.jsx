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
            console.log({newLesson})
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

    if (loading) return <MoonLoader color="#4C0B16" speedMultiplier={0.75}/>;

    return(
        <>
        <div>
            <h2>{userData.name}</h2>
            <button onClick={handleOpen}>Add Lesson</button>
            <Modal isOpen={open} onClose={handleClose}>
                <>
                    <h1>Add New Lesson</h1>
                    <form onSubmit={newLesson}>
                        <label>
                            Name: <input name="LessonName" />
                        </label>
                        <br/>
                        <label>
                            Day: <select name="lessonDay">
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
                            Start Time: <input type="time" name="startTime" />
                        </label>
                        <br/>
                        <label>
                            End Time: <input type="time" name="endTime" />
                        </label>
                        <br/>
                        <button type="submit">Add</button>
                        <button type="button" onClick={handleClose}>Cancel</button>
                        {error && (
                            <span style={{color: 'red' }}>
                                {error}
                            </span>
                        )}
                    </form>
                </>
            </Modal>
        </div>
        {lessons.map((lesson, index) => (
            <LessonCard
                key={index}
                lesson={lesson}
                index={index}
                onDelete={deleteLesson}
                onEdit={editLesson}
            />
        ))}
        </>
    )
}

export default UserPanel