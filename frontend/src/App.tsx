import React, { useState } from "react";
import UserList from "./components/UserList";
import UserForm from "./components/UserForm";
import { User } from "./types/user";
import { Container, Grid, Button, Typography, Box } from "@mui/material";
import axios from "axios";

const App: React.FC = () => {
    const [editingUser, setEditingUser] = useState<User | null>(null);
    const [showForm, setShowForm] = useState<boolean>(false);

    const handleCreateUser = async (user: User) => {
        try {
            if (user.id) {
                // Atualizar usuário existente
                await axios.put(`/api/users/${user.id}`, user);
            } else {
                // Criar novo usuário
                await axios.post("/api/users", user);
            }
            setShowForm(false);
            setEditingUser(null);
        } catch (error) {
            console.error("Failed to save user");
        }
    };

    const handleEditUser = (user: User) => {
        setEditingUser(user); // Prepara o usuário para edição
        setShowForm(true);
    };

    const handleCancelEdit = () => {
        setEditingUser(null); // Limpa a edição
        setShowForm(false);
    };

    return (
        <Container maxWidth="md" sx={{ marginTop: 5 }}>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Typography variant="h4" align="center" gutterBottom>
                        User Management
                    </Typography>
                </Grid>

                <Grid item xs={12} alignItems="center">
                    {!showForm ? (
                        <Box display="flex" justifyContent="flex-end" mb={3}>
                            <Button variant="contained" color="primary" onClick={() => setShowForm(true)}>
                                Add New User
                            </Button>
                        </Box>
                    ) : null}
                </Grid>

                <Grid item xs={12}>
                    {showForm ? (
                        <UserForm
                            initialUserData={editingUser}
                            onSubmit={handleCreateUser}
                            onCancel={handleCancelEdit}
                        />
                    ) : (
                        <UserList onEditUser={handleEditUser} />
                    )}
                </Grid>
            </Grid>
        </Container>
    );
};

export default App;
