export default interface SocialUser {
    provider: string,
    id: string,
    email: string,
    name: string, 
    token?: string,
    idToken?: string
}