import exp from "constants";
import { on } from "events";
import { useState } from "react"
import axios, { Axios, AxiosRequestConfig, AxiosResponse } from 'axios'



const User = () => {
    const [values, setValues] = useState({
        id: "",
        username: "",
        password: "",
        age: 0,
        role: "",
    })

    const onChange = (e: React.FormEvent<HTMLInputElement>) => {
        setValues({
            ...values,
            [e.currentTarget.name]: e.currentTarget.value,
        });
    };

    const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        axios.post("http://localhost:8080/users", values)
        .then(res => {console.log(res)});
    }
    return (
        <div>
            <form onSubmit={onSubmit}>
                <Input name="id" onChange={onChange}/>
                <Input name="username" onChange={onChange}/>
                <Input name="password" onChange={onChange}/>
                <Input name="email" onChange={onChange}/>
                <Input name="age" onChange={onChange}/>
                <Input name="role" onChange={onChange}/>
                <button>회원가입</button>
            </form>
        </div>
    );
}

const Input = (props: {name: string, onChange: (e: React.FormEvent<HTMLInputElement>) => void }) => {
    return (
        <input
                    name={props.name}
                    onChange={props.onChange}
                    type="text"
                    placeholder={props.name}
                />
    )
}

export default User;

