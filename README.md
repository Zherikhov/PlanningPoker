# Planning Poker

Planning Poker is a web-based tool for agile teams to estimate the effort of user stories and tasks collaboratively. It provides a lightweight interface for creating planning sessions, inviting team members, and collecting estimates using standard Fibonacci cards. Results are aggregated and displayed after everyone has voted.

## Key features

- Create and manage rooms for planning sessions.
- Invite team members via shareable links; guests can join without registration.
- Add tasks manually or import them from your issue tracker (e.g., Jira).
- Real-time voting with hidden cards; results revealed simultaneously.
- Support for asynchronous voting for distributed teams.
- Save estimates back to your issue tracker once the session is finished.

## Future enhancements

- Integration with Jira to import backlog items and update story points automatically.
- AI assistant to summarize discussion and suggest estimates based on historical data.
- Mobile-friendly interface and optional voice commands for quick voting.

This repository contains the backend implementation built with Java and Spring Boot. See `src/` for source code and the `backend` module for service configuration.

## Authentication

- `POST /api/auth/login` issues an access token and optional refresh cookie.
- `POST /api/auth/logout` clears the refresh token cookie and should be called when the user signs out.
