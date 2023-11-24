import exp from "constants";
import { on } from "events";
import { useState } from "react"
import axios, { Axios, AxiosRequestConfig, AxiosResponse } from 'axios'



const LoginForm = () => {
    const [values, setValues] = useState({
        username: "",
        password: "",
    })

    const onChange = (e: React.FormEvent<HTMLInputElement>) => {
        setValues({
            ...values,
            [e.currentTarget.name]: e.currentTarget.value,
        });
    };

    const onSubmit = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        event.preventDefault();
        axios.post("http://localhost:8080/api/login", values,
            {
                headers: {
                    "Access-Control-Allow-Origin": "http://localhost:3000"
                },
            })
            .then(res => { console.log(res) });
    }
    return (
        <>
            <input type="text" placeholder="id" name="username" onChange={onChange}></input>
            <input type="text" placeholder="password" name="password" onChange={onChange}></input>
            <button className="btn btn-primary" onClick={onSubmit} >로그인</button>
        </>
    );
}



export default LoginForm;

