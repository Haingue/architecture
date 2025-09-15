export type Point = {
    lineIdx: number,
    columnIdx: number
}

export type MoveDto = {
  gameCode: string,
  from: Point,
  to: Point
}

export type Color = "WHITE" | "BLACK"

export type PieceType = "Rook" | "Bishop" | "Knight" | "King" | "Queen" | "Pawn"

export type Piece = {
    name?: PieceType,
    color: Color,
    alive: true,
    transFormed: false
}

export type Player = {
    color: Color,
    name: string
}

export type Board = {
    gameCode: string,
    cells: Piece[],
    turn: number,
    currentPlayer: Player
}