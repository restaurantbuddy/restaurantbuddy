import {NextResponse as res} from "next/server";

export default function Logout() {
    res.redirect("/customer");
}