

let reimbursementResourceURL = "http://localhost:8080/ERS/Reimbursement";


async function createReimbursement(newReimbursement) {
    return await fetch(
        reimbursementResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newReimbursement)
        }
    );

    //return response;
}

async function getReimbursementByUser(authorization) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "mode": "user"
            },
            body: JSON.stringify(authorization)
        }
    );

    return response;
}

async function getReimbursementByStatus(status) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "mode": "status"
            },
            body: JSON.stringify(status)
        }
    );

    return response;
}

async function getReimbursementByPendingUser(authorization) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "mode": "pendinguser"
            },
            body: JSON.stringify(authorization)
        }
    );

    return response;
}

async function editReimbursement(reimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "mode": "amount"
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}

async function completeReimbursement(reimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "mode": "completed"
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}

async function deleteReimbursement(reimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}