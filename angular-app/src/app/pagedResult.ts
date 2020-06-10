export interface PagedResult<T> {
    content: T[];
    totalElements: number;
    totalPages: number;
    last: boolean;
    first: boolean;
    'number': number; // Evita conflito do uso da palavra reservada
    numberOfElements: number;
    size: number;
}