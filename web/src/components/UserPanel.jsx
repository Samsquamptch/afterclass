import { useState, useEffect } from "react"
import { getUser } from "../api/users"
import { MoonLoader } from "react-spinners";
import { removeLesson } from "../api/lessons";

function UserPanel() {

    const [loading, setLoading] = useState(true);
    const [userData, setUserData] = useState(null)
    const [lessons, setLessons] = useState(null)

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

    const  deleteLesson = (index) => {
        async function removeCheck(i) {
            return await removeLesson(i)
        }
        const wasRemoved = removeCheck(index)
        if (wasRemoved) {
            const updatedLessons = lessons.filter((_, i) => i !==index)
            setLessons(updatedLessons);
        }
    }

    if (loading) return <MoonLoader color="#4C0B16" speedMultiplier={0.75}/>;

    return(
        <>
        <div>
            <h2>{userData.name}</h2>
            <button>Add Lesson</button>
        </div>
        {lessons.map((lesson, index) => (
            <div key={lesson.id || index} className="lesson-card">
                <p>Name: {lesson.name}</p>
                <p>Day: {lesson.weekday}</p>
                <p>Start: {lesson.startTime}</p>
                <p>End: {lesson.endTime}</p>
                <button>Edit</button>
                <button onClick={() => deleteLesson(index)}>Delete</button>
            </div>
            ))}
        </>
    )
}

export default UserPanel