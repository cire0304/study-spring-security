import exp from "constants";
import { on } from "events";
import { useEffect, useState } from "react"
import axios, { Axios, AxiosRequestConfig, AxiosResponse } from 'axios'



const Message = () => {
    const [value, setValue] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/api/message")
        .then(res => setValue(res.data));
    },[value])

    return (
        <div>
            {value}
        </div>
    );
}

export default Message;

