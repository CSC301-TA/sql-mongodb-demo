import React, { useEffect, useState } from "react";

import TaskList from "../components/TaskList";

export default function Index() {
  const [tasksList, setTasksList] = useState([]);

  useEffect(() => {
    fetch("http://localhost:3001/api/todos", {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })
      .then((res) => res.json())
      .then((data) => setTasksList(data));
  }, []);

  return (
    <div className="page-container">
      <TaskList tasksList={tasksList} />
    </div>
  );
}
