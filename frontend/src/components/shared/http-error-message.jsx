export const HttpErrorMessage = (props) => {
    return (<>
            <div className="sub-content rounded-corners inner-color error-message">
                <h1>{props.errorCode} - {props.details}</h1>
                <p>{props.description}</p>
            </div>
        </>
    )
}