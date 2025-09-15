import type { MoveDto } from "../types/ChessApiTypes"


export function createGame(): Promise<Response> {
    return fetch('http://localhost:8080/chess',
        {
            method: 'POST'
        }
    )
}

export function getGame(gameCode: string): Promise<Response> {
    const params = new URLSearchParams({gameCode})
    return fetch(`http://localhost:8080/chess?${params.toString()}`)
}

export function sendMove(move: MoveDto): Promise<Response> {
    return fetch(`http://localhost:8080/chess`, {
        method: 'PUT',
        body: JSON.stringify(move)
    })
}