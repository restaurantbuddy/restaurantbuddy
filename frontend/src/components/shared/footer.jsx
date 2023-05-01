import Link from "next/link";

export const Footer = () => {
    return (<>
            <footer className="rounded-corners inner-color">
                <p><Link href="/">Return to Customer Landing Page</Link></p>
                <p>Copyright &copy; 2023 - Samuel Mace</p>
            </footer>
        </>
    )
}