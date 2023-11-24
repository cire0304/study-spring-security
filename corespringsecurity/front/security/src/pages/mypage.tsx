import exp from "constants";
import { on } from "events";
import { useEffect, useState } from "react"
import axios, { Axios, AxiosRequestConfig, AxiosResponse } from 'axios'



const Mypage = () => {
    const [value, setValue] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/api/mypage")
        .then(res => setValue(res.data));
    },[value])

    return (
        <div>
            {value}
        </div>
    );
}

export default Mypage;

