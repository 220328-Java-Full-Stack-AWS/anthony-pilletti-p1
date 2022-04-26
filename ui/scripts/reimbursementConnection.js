

let reimbursementResourceURL = "http://localhost:8080/ERS/Reimbursement";


async function createReimbursement(newReimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newReimbursement)
        }
    );

    return response;
}

async function getReimbursementByUser(authDto) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "mode": "user"
            }
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
            }
        }
    );

    return response;
}

async function getReimbursementByPendingUser(authDto) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "mode": "pendinguser"
            }
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