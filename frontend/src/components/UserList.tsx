import React, { useState, useEffect } from "react";
import { User } from "../types/user";
import {
    CircularProgress,
    Container,
    IconButton,
    List,
    ListItem,
    ListItemText,
    Paper,
    Typography,
} from "@mui/material";
import axios from "axios";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

interface UserListProps {
    onEditUser: (user: User) => void;
}

const UserList: React.FC<UserListProps> = ({ onEditUser }) => {
    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get<User[]>("/api/users");
            setUsers(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Failed to fetch users");
            setLoading(false);
        }
    };

    const deleteUser = async (id: number) => {
        try {
            await axios.delete(`/api/users/${id}`);
            fetchUsers(); // Recarregar usuários após exclusão
        } catch (error) {
            console.error("Failed to delete user");
        }
    };

    if (loading) return <CircularProgress />;

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>
                User List
            </Typography>
            <Paper elevation={3} sx={{ padding: "10px" }}>
                <List>
                    {users.map((user) => (
                        <ListItem key={user.id}>
                            <ListItemText primary={user.name} secondary={user.email} />
                            <IconButton onClick={() => onEditUser(user)}>
                                <EditIcon />
                            </IconButton>
                            <IconButton onClick={() => deleteUser(user.id)}>
                                <DeleteIcon />
                            </IconButton>
                        </ListItem>
                    ))}
                </List>
            </Paper>
        </Container>
    );
};

export default UserList;
