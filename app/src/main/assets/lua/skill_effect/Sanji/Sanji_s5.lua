--v3
--[[
Sanji
0
0
siwangbaoxue
1
{default,gongji6,1,0,0,87,1.000000}
1
{default,0.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}
0

0

1
{0,Sanji,2,0,0.000000,0.000000,87,0}
1
{default,texiao1,1,0,0,87,1.000000}
1
{default,400.000000,280.000000,400.000000,280.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,0.850000,0.850000,0.850000,0.850000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji6",1.000000,0,87,1}}, 
pos_sequence      = {{0.000000,0.000000,-400.000000,0.000000,1,1,0,1,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"Sanji",2,0,0.000000,0.000000,{{"texiao1",1.000000,0,87,1}},{{400.000000,280.000000,400.000000,280.000000,2,2,0,1,0.000000,0,0,0,0}},{{0.850000,0.850000,0.850000,0.850000,0,0,1}},{},0}}, 
bloodNum          = 3,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "siwangbaoxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
