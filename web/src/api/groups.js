async function getGroup() {
    try {
    const response = await fetch('/data.json');
    const data = await response.json();
    return data.group;
  } catch (error) {
    console.error('Error fetching group data:', error);
    return null;
  }
}

function createGroup(name) {
    console.log(name)
}

export {createGroup, getGroup}