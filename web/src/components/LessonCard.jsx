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
        <div key={lesson.id || index} className="lesson-card">
          <form onSubmit={saveLessonChanges}>
            <p>
              <label>
                Name: <input name="LessonName" defaultValue={lessonName} />
              </label>
            </p>
            <p>
              <label>
                Day:{" "}
                <select name="lessonDay" defaultValue={lessonDay}>
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
                  type="time"
                  name="startTime"
                  defaultValue={lessonStart}
                />
              </label>
            </p>
            <p>
              <label>
                End Time:{" "}
                <input type="time" name="endTime" defaultValue={lessonEnd} />
              </label>
            </p>
            <p>
              <button type="submit">Save</button>
              <button type="button" onClick={() => setEditState(false)}>
                Cancel
              </button>
            </p>
            {error && <span style={{ color: "red" }}>{error}</span>}
          </form>
        </div>
      );
    } else {
      return (
        <div key={lesson.id || index} className="lesson-card">
          <p>Name: {lessonName}</p>
          <p>Day: {lessonDay}</p>
          <p>Start: {lessonStart}</p>
          <p>End: {lessonEnd}</p>
          <button onClick={() => setEditState(true)}>Edit</button>
          <button onClick={() => deleteLesson()}>Delete</button>
        </div>
      );
    }
  };

  return <>{renderComponent()}</>;
}

export default LessonCard;
