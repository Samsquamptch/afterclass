import { useState, useEffect } from "react";
import { updateLesson, removeLesson } from "../api/lessons";

function LessonCard({ lesson, index, onDelete, onEdit }) {
  const [editState, setEditState] = useState(false);
  const [error, setError] = useState("");
  const [lessonName, setLessonName] = useState(lesson.name);
  const [lessonDay, setlessonDay] = useState(lesson.weekday);
  const [lessonStart, setlessonStart] = useState(lesson.startTime);
  const [lessonEnd, setlessonEnd] = useState(lesson.endTime);
  const [updateCheck, setUpdateCheck] = useState(false)

  const deleteLesson = async () => {
    const wasRemoved = await removeLesson(index)
    if (wasRemoved) {
        onDelete(index);
    }
  }

  const saveLessonChanges = async (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const name = formData.get("LessonName");
    const day = formData.get("lessonDay");
    const start = formData.get("startTime");
    const end = formData.get("endTime");
    if (name === "" || start === "" || end === "") {
      setError("Please ensure no fields are empty");
      return;
    }
    let updatedLesson = {
      name: name,
      weekday: day,
      startTime: start,
      endTime: end,
    };
    const success = await updateLesson(updatedLesson);
    if (success) {
      setLessonName(name);
      setlessonDay(day);
      setlessonStart(start);
      setlessonEnd(end);
      setEditState(false);
      setUpdateCheck(true)
    }
  };

  useEffect(() => {
  if (!editState && updateCheck) {
    onEdit();
    setUpdateCheck(false);
  }
}, [editState, updateCheck]);

  const renderComponent = () => {
    if (editState) {
      return (
        <div key={lesson.id || index} className="w-5/6 rounded-2xl p-2 my-1">
          <form onSubmit={saveLessonChanges}>
            <p>
              <label>
                Name: <input className="bg-white" name="LessonName" size="16" defaultValue={lessonName} />
              </label>
            </p>
            <p>
              <label>
                Day:{" "}
                <select className="bg-white" name="lessonDay" defaultValue={lessonDay}>
                  <option value="Monday">Monday</option>
                  <option value="Tuesday">Tuesday</option>
                  <option value="Wednesday">Wednesday</option>
                  <option value="Thursday">Thursday</option>
                  <option value="Friday">Friday</option>
                  <option value="Saturday">Saturday</option>
                  <option value="Sunday">Sunday</option>
                </select>
              </label>
            </p>
            <p>
              <label>
                Start Time:{" "}
                <input
                  className="bg-white"
                  type="time"
                  name="startTime"
                  defaultValue={lessonStart}
                />
              </label>
            </p>
            <p>
              <label>
                End Time:{" "}
                <input 
                className="bg-white"
                type="time"
                name="endTime"
                defaultValue={lessonEnd} />
              </label>
            </p>
            <p>
              <button className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-sm mt-1 mr-1"
              type="submit">Save</button>
              <button type="button" 
              className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-sm ml-1"
              onClick={() => setEditState(false)}>
                Cancel
              </button>
            </p>
            {error && <span style={{ color: "red" }}>{error}</span>}
          </form>
        </div>
      );
    } else {
      return (
        <div key={lesson.id || index} className="w-5/6 rounded-2xl p-2 my-1">
          <p className="text-2xl">{lessonName}</p>
          <p>Day: {lessonDay}</p>
          <p>Start: {lessonStart}</p>
          <p>End: {lessonEnd}</p>
          <button 
          className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-sm mt-1 mr-1"
          onClick={() => setEditState(true)}>Edit</button>
          <button 
          className="bg-birkbeck hover:bg-hover rounded-none outline-2 outline-black outline-solid p-1 text-white text-sm ml-1"
          onClick={() => deleteLesson()}>Delete</button>
        </div>
      );
    }
  };

  return <>{renderComponent()}</>;
}

export default LessonCard;
