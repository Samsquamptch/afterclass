async function removeLesson(lessonId) {
    try {
        const response = await fetch('/data.json');
        const data = await response.json();
        const user = data.group.users.find(u => u.id === 2);
        return user.lessons.find(l => l.id === lessonId) !== null ? true : false
    } catch (error) {
        console.error('Error finding lesson:', error);
        return false;
    }
}

async function addLesson(lesson) {
    return {
        id: Math.floor(Math.random() * 100) + 1,
        name: lesson.name,
        weekday: lesson.weekday,
        startTime: lesson.startTime,
        endTime: lesson.endTime
    }
}

async function updateLesson(lesson) {
    console.log(lesson)
    return true
}

export {removeLesson, addLesson, updateLesson}